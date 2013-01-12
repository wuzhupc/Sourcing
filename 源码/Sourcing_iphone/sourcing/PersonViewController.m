//
//  PersonViewController.m
//  sourcing
//
//  Created by wuzhu on 13-1-4.
//  Copyright (c) 2013年 wuzhu. All rights reserved.
//

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Imports

#import "PersonViewController.h"
#import "CustomNavigationBar.h"
#import "CustomNavigationBar.h"
#import "ChannelVO.h"
#import "FileUtil.h"
#import "JsonParser.h"
#import "MobileInfoService.h"
#import "ApplicationSet.h"
#import "UIColor+MGExpanded.h"
#import "StyledTableViewCell.h"
#import "ToastHintUtil.h"
#import "StringUtil.h"
#import "NewsDetailViewController.h"
#import "ProjectCell.h"
#import "TrainCell.h"

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Types

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Defines & Constants

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Macros

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Private Interface

@interface PersonViewController ()

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Private Properties

@end


////////////////////////////////////////////////////////////////////////////////
#pragma mark - Implementation

@implementation PersonViewController

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Synthesize

/* Outlets ********************************************************************/

/* Public *********************************************************************/

/* Private ********************************************************************/

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Setup & Teardown

- (void)commonInitPersonViewController
{
    nowSelChannel = -1;
    [self.tabBarItem setFinishedSelectedImage:[UIImage imageNamed:@"icon_home_tb_person"] withFinishedUnselectedImage:[UIImage imageNamed:@"icon_home_tb_person"]];
}

- (id)initWithNibName:(NSString*)nibNameOrNil bundle:(NSBundle*)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self)
    {
        [self commonInitPersonViewController];
    }
    return self;
}

- (id)initWithCoder:(NSCoder*)aDecoder
{
    self = [super initWithCoder:aDecoder];
    if (self)
    {
        [self commonInitPersonViewController];
    }
    return self;
}

- (void)viewDidLoad {
    [super viewDidLoad];
    // your code here
    [self initTableView];
    [self initSubChannels];
}

- (void)viewDidUnload {
	// your code here
    subChannels = nil;
    subChannelBTV = nil;
    prTableView = nil;
    [self setSearchkeySearchBar:nil];
    [super viewDidUnload];
}

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Superclass Overrides

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Public methods

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Private methods

-(void)initTableView
{
    CGRect bounds = CGRectMake(0, 124, self.view.frame.size.width, self.view.frame.size.height);
    bounds.size.height -= 124.0f;
    prTableView = [[PullingRefreshTableView alloc] initWithFrame:bounds pullingDelegate:self];
    
    prTableView.backgroundColor = CCOLOR_TABLEVIEW_BG;
    prTableView.separatorStyle = UITableViewCellSeparatorStyleNone;
    prTableView.separatorColor = CCOLOR_TABLEVIEW_SEL;
    prTableView.dataSource = self;
    prTableView.delegate = self;
    [self.view addSubview:prTableView];
}


-(void)initSubChannels
{
    if (self.fatherchannel!=nil) {
        subChannels = [ChannelVO getChannelsWithFatherID:[ApplicationSet shareData].channels father:self.fatherchannel.ChannelID];
    }
    if(subChannels==nil||[subChannels count]==0)
    {
        [self.subChannelScrollView setHidden:YES];
    }
    else
    {
        [self.subChannelScrollView setHidden:NO];
        NSMutableArray *channelnames = [[NSMutableArray alloc] initWithCapacity:[subChannels count]];
        for (ChannelVO *vo in subChannels) {
            [channelnames addObject:vo.channelName];
        }
        //TEST
        //[channelnames addObject:@"AAAA"];
        //[channelnames addObject:@"BBBB"];
        //
        subChannelBTV = [[BrowserTabView alloc] initWithTabTitles:channelnames andDelegate:self];
        
        [self.subChannelScrollView addSubview:subChannelBTV];
        //NSLog(@"%f %f",subChannelBTV.frame.size.height,subChannelBTV.frame.size.width);
        
        [self.subChannelScrollView setContentSize:subChannelBTV.frame.size];
    }
}

-(ChannelVO *)getNowChannel
{
    if(subChannels==nil||[subChannels count]==0||nowSelChannel<0||[subChannels count]<=nowSelChannel)
    {
        return nil;
    }
    return [subChannels objectAtIndex:nowSelChannel];
}


-(void)loadData:(NSString *)ksearch
{
    ChannelVO *cvo = [self getNowChannel];
    if(cvo==nil)
    {
        [prTableView tableViewDidFinishedLoading];
        return;
    }
    MobileInfoService *service = [[MobileInfoService alloc] initWithDelegate:self tag:CINT_TAG_LOADNEWDATA];
    [service setTag2:cvo.ChannelID];
    //TODO
    //[service getNewsList:[NSString stringWithFormat:@"%d",cvo.type] bottomid:@"0"];
}
-(void)loadMoreData:(NSString *)ksearch
{
    ChannelVO *cvo = [self getNowChannel];
    if(cvo==nil)
    {
        [prTableView tableViewDidFinishedLoading];
        return;
    }
    MobileInfoService *service = [[MobileInfoService alloc] initWithDelegate:self tag:CINT_TAG_LOADMOREDATA];
    [service setTag2:cvo.ChannelID];
    //TODO
    //[service getNewsList:[NSString stringWithFormat:@"%d",cvo.type] bottomid:[NSString stringWithFormat:@"%d",[self getBottomid]]];
}

-(void)setTableViewData:(NSMutableArray *)list
{
    datalist = list;
    if(datalist == nil || [datalist count]==0)
    {
        [prTableView setSeparatorColor:[UIColor clearColor]];
        [prTableView setHeaderOnly:YES];
    }
    else
    {
        [prTableView setSeparatorColor:CCOLOR_TABLEVIEW_SEL];
        if ([datalist count]%CINT_PAGE_SIZE_DEFAULT == 0) {
            [prTableView setHeaderOnly:NO];
        }
        else{
            [prTableView setHeaderOnly:YES];
        }
    }
    [prTableView reloadData];
}

-(void)addTableViewData:(NSMutableArray *)list
{
    if(list==nil||[list count]==0)
        return;
    if(datalist==nil||[datalist count]==0)
    {
        [self setTableViewData:list];
        return;
    }
    NSMutableArray *insertIndexPaths = [NSMutableArray arrayWithCapacity:[list count]];
    for(int i = 0 ;i < [list count];i++)
    {
        NSIndexPath *newPath=[NSIndexPath indexPathForRow:[datalist count]+i inSection:0];
        [insertIndexPaths addObject:newPath];
    }
    [datalist addObjectsFromArray:list];
    [prTableView setSeparatorColor:CCOLOR_TABLEVIEW_SEL];
    if ([datalist count]%CINT_PAGE_SIZE_DEFAULT == 0) {
        [prTableView setHeaderOnly:NO];
    }
    else{
        [prTableView setHeaderOnly:YES];
    }
    //重新呼叫UITableView的方法, 來生成行.
    [prTableView beginUpdates];
    [prTableView insertRowsAtIndexPaths:insertIndexPaths withRowAnimation:UITableViewRowAnimationFade];
    [prTableView endUpdates];
}

-(NSInteger)getBottomid
{
    if(datalist==nil||[datalist count]==0)
        return 0;
    //TODO
    BaseVO *vo = [datalist objectAtIndex:[datalist count]-1];
    return vo.Id;
}
////////////////////////////////////////////////////////////////////////////////
#pragma mark - Actions

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Delegate methods

-(void)serviceResult:(ResponseVO *)result
{
    [prTableView tableViewDidFinishedLoading];
    //判断是否是当前栏目的申请，如果不是，则只在成功时增加数据缓存
    ChannelVO *vo = [self getNowChannel];
    if(result.tag2 != vo.ChannelID)
    {
        if ([result isSucess]&&result.tag == CINT_TAG_LOADNEWDATA) {
            //TODO 缓存数据
        }
        return;
    }
    if(![result isSucess])
    {
        [ToastHintUtil showHint:result.msg parentview:self.view];
        return;
    }
    ResponseVO *rvo = [[ResponseVO alloc] init];
    NSMutableArray *list = [JsonParser parseJsonToList:result.msg respVO:&rvo ref:nil];
    if(![rvo isSucess])
    {
        [ToastHintUtil showHint:rvo.msg parentview:self.view];
        return;
    }
    if (result.tag == CINT_TAG_LOADNEWDATA) {
        [self setTableViewData:list];
    }else
    {
        [self addTableViewData:list];
    }
}

-(void)BrowserTabView:(BrowserTabView *)browserTabView didSelecedAtIndex:(NSUInteger)index
{
    if (subChannels==0||index>[subChannels count]||nowSelChannel == index) {
        return;
    }
    ChannelVO *newselVO = [subChannels objectAtIndex:index];
    NSLog(@"%@",newselVO.channelName);
    //切换时清除表数据
    [prTableView setUpdateRefreshDate:nil];
    [self setTableViewData:nil];
    //TODO 读取缓存数据
    nowSelChannel = index;
    [prTableView launchRefreshing];
}

-(NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    return 1;
}

-(NSInteger)tableView:(UITableView *)tv numberOfRowsInSection:(NSInteger)section
{
    if(datalist == nil)
    {
        //加载时移除没有数据显示
        if([(PullingRefreshTableView *)tv isLoading])
            return 0;
        
        //未加载时显示没有数据
        return 1;
    }
    return [datalist count];
}

-(CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath
{
    if(datalist == nil||[datalist count]==0)
        return 80.0f;
    BaseVO *vo = [datalist objectAtIndex:indexPath.row];
    return [vo heightForCell:indexPath.row];
    
}

-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    if(datalist == nil||[datalist count]==0)
    {
        if([(PullingRefreshTableView *)tableView isLoading])
            return [BaseVO tableViewWithEmptyData:tableView title:@""];
        else
            return [BaseVO tableViewWithEmptyData:tableView title:NSLocalizedString(@"没有数据,请偿试下拉刷新数据",@"没有数据时提示")];
    }
    BaseVO *vo = [datalist objectAtIndex:indexPath.row];
    return [vo tableView:tableView index:indexPath.row];
}


-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{
    [tableView deselectRowAtIndexPath:indexPath animated:YES];
    if(datalist==nil||[datalist count]==0)
        return;
    BaseVO *vo = [datalist objectAtIndex:indexPath.row];
    ChannelVO *cvo = [self getNowChannel];
    NewsDetailViewController *vc = [[NewsDetailViewController alloc] initWithBaseVO:vo title:[NSString stringWithFormat:@"%@详情",cvo.channelName]];
    [self presentModalViewController:vc animated:YES];
}

//下拉刷新
-(void)pullingTableViewDidStartRefreshing:(PullingRefreshTableView *)tableView
{
    //TODO
    [self performSelector:@selector(loadData:) withObject:@""];
}
//上拉加载更多
-(void)pullingTableViewDidStartLoading:(PullingRefreshTableView *)tableView
{
    [self performSelector:@selector(loadMoreData:) withObject:@""];
}

-(void)scrollViewDidScroll:(UIScrollView *)scrollView
{
    [prTableView tableViewDidScroll:scrollView];
}

-(void)scrollViewDidEndDragging:(UIScrollView *)scrollView willDecelerate:(BOOL)decelerate
{
    [prTableView tableViewDidEndDragging:scrollView];
}
@end

//
//  FavInfoController.m
//  sourcing
//
//  Created by wuzhu on 13-1-15.
//  Copyright (c) 2013年 wuzhu. All rights reserved.
//

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Imports

#import "FavInfoController.h"
#import "FavoriteUtil.h"
#import "ApplicationSet.h"
#import "UIColor+MGExpanded.h"
#import "BaseVO.h"
#import "NewsDetailViewController.h"

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Types

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Defines & Constants

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Macros

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Private Interface

@interface FavInfoController ()

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Private Properties
@property (nonatomic) BOOL isFirstStart;
@end


////////////////////////////////////////////////////////////////////////////////
#pragma mark - Implementation

@implementation FavInfoController

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Synthesize

/* Outlets ********************************************************************/

/* Public *********************************************************************/

/* Private ********************************************************************/
@synthesize isFirstStart = isFirstStart_;
////////////////////////////////////////////////////////////////////////////////
#pragma mark - Setup & Teardown

- (void)commonInitFavInfoController
{
    self.isFirstStart = YES;
}

- (id)initWithNibName:(NSString*)nibNameOrNil bundle:(NSBundle*)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self)
    {
        [self commonInitFavInfoController];
    }
    return self;
}

- (id)initWithCoder:(NSCoder*)aDecoder
{
    self = [super initWithCoder:aDecoder];
    if (self)
    {
        [self commonInitFavInfoController];
    }
    return self;
}

- (void)viewDidLoad {
    [super viewDidLoad];
    // your code here
    self.tableviewFav.backgroundColor = CCOLOR_TABLEVIEW_BG;
    self.tableviewFav.separatorStyle = UITableViewCellSeparatorStyleNone;
    self.tableviewFav.separatorColor = CCOLOR_TABLEVIEW_SEL;
}

-(void)viewWillAppear:(BOOL)animated
{
    [super viewWillAppear:animated];
    //判断是否需要重新载入（初始是不用的）
    if(self.isFirstStart)
    {
        self.isFirstStart = NO;
    }
    else
    {
        [self.tableviewFav reloadData];
    }
}

- (void)viewDidUnload {
	// your code here
    [self setLabelTitle:nil];
    [self setTableviewFav:nil];
    [super viewDidUnload];
}

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Superclass Overrides

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Public methods

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Private methods

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Actions
- (IBAction)actionReturn:(id)sender {
    [self dismissModalViewControllerAnimated:YES];
}

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Delegate methods
-(NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    return  1;
}

-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    NSInteger resutlt = [[FavoriteUtil favoriteUtil] getFavNumber];
    if(resutlt == 0 )
        return 1;
    return resutlt;
}

-(CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath
{
    NSInteger resutlt = [[FavoriteUtil favoriteUtil] getFavNumber];
    if(resutlt == 0 )
        return 80.0f;
    BaseVO *vo = [[FavoriteUtil favoriteUtil].dataList objectAtIndex:indexPath.row];
    return [vo heightForCell:indexPath.row allowheadline:NO];
}

-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    NSInteger resutlt = [[FavoriteUtil favoriteUtil] getFavNumber];
    if(resutlt == 0 )
    {
        [BaseVO tableViewWithEmptyData:tableView title:NSLocalizedString(@"没有收藏数据",@"没有数据时提示")];
    }
    BaseVO *vo = [[FavoriteUtil favoriteUtil].dataList objectAtIndex:indexPath.row];
    return [vo tableView:tableView index:indexPath.row allowheadline:NO];
}

-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{
    [tableView deselectRowAtIndexPath:indexPath animated:YES];
    NSInteger resutlt = [[FavoriteUtil favoriteUtil] getFavNumber];
    if(resutlt == 0 )
        return;
    BaseVO *vo = [[FavoriteUtil favoriteUtil].dataList objectAtIndex:indexPath.row];
    NewsDetailViewController *vc = [[NewsDetailViewController alloc] initWithBaseVO:vo title:NSLocalizedString(@"收藏详情", @"详情页标题")];
    [self presentModalViewController:vc animated:YES];
}

@end

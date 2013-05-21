//
//  NewsVO.m
//  sourcing
//
//  Created by wuzhu on 12-12-26.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "NewsVO.h"
#import "StringUtil.h"
#import "HtmlUtil.h"
#import "MobileInfoService.h"
#import "ResponseVO.h"
#import "JsonParser.h"
#import "NewsDetailVO.h"
#import "NewsNormalCell.h"
#import "NewsHeadlineCell.h"
#import "NewsNoPicCell.h"
#import "FileUtil.h"
#import "TimeUtil.h"

@interface NewsVO ()
{
@private
    NSInteger newsid;
    NSString *newssummary;
}

@end

@implementation NewsVO

@synthesize headline = headline_;
//@synthesize newssummary = newssummary_;
@synthesize title = title_;
@synthesize titlepic = titlepic_;
@synthesize newstype = newstype_;
@synthesize titlepic_small = titlepic_small_;
@synthesize publishtime = publishtime_;
@synthesize auther = auther_;
@synthesize source = source_;
@synthesize readernum = readernum_;
@synthesize commentnum = commentnum_;

-(void)setNewsid:(NSInteger )kid
{
    newsid = kid;
    self.Id = kid;
}
-(NSInteger )Newsid
{
    return newsid;
}

#pragma mark - public methods

-(void)setNewssummary:(NSString *)summary
{
    if([StringUtil isEmptyStr:summary])
    {
        newssummary = summary;
        return;
    }
    newssummary =[summary stringByReplacingOccurrencesOfString:@"&nbsp;" withString:@" "];
    newssummary = [newssummary stringByReplacingOccurrencesOfString:@"\r\n" withString:@" "];
}
-(NSString *)getNewssummary
{
    return newssummary;
}

-(BOOL)isHeadline
{
    if([StringUtil isEmpty:self.headline])
        return NO;
    return [self.headline isEqualToString:@"1"] || [[self.headline lowercaseString] isEqualToString:@"true"];
}
#pragma mark - Superclass Overrides
-(NSString *)getHtmlTitle
{
    return [NSString stringWithFormat:@"<br/><div align=\"center\"><font color=\"#111111\" size=\"4pt\"><strong>%@</strong></font></div><br/>",self.title];
}
-(NSString *)getHtmlSubTitle
{
    return [NSString stringWithFormat:@"<div align=\"center\"><font color=\"#666666\" size=\"2pt\">%@&nbsp;&nbsp; 来源:%@</font></div><div style=\"height:0;border-bottom:1px solid #f00\"></div>",self.publishtime,self.source];
}

-(void)setHtmlToShow:(DetailInfoCompleteBlock)kcomblock failure:(DetailInfoErrorBlock)kerrorblock
{
    [super setHtmlToShow:kcomblock failure:kerrorblock];
    //判断读取缓存
    NSError* error = nil;
    NSString *cachecontent = [NSString stringWithContentsOfFile:[self getCacheFileName] encoding:NSUTF8StringEncoding error:&error];
    if(error==nil&&![StringUtil isEmptyStr:cachecontent])
    {
        ResponseVO *rvo = [[ResponseVO alloc] init];
        NewsDetailVO *vo = [JsonParser parseJsonToEntity:cachecontent respVO:&rvo ref:nil];
        if([rvo isSucess])
        {
            if(_comblock) _comblock(vo);
            return;
        }
    }
    MobileInfoService *service = [[MobileInfoService alloc] initWithDelegate:self tag:CINT_TAG_GETNEWSDETAIL];
    service.tag2 = newsid;
    [service getNewsDetail:[NSString stringWithFormat:@"%d",self.newstype] newsid:[NSString stringWithFormat:@"%d",newsid]];
    
}
-(NSString *)generateShareText
{
    return self.title;
}

-(CGFloat)heightForCell:(NSInteger)kindex allowheadline:(BOOL)kallow
{
    if(kindex==0&&kallow&&[self isHeadline])
        return 120.0f;
    return 80.0f;
}

-(CGFloat)heightForCell:(NSInteger)kindex
{
    return [self heightForCell:kindex allowheadline:YES];
}

-(UITableViewCell *)tableView:(UITableView *)tableView index:(NSInteger)kindex
{
    return [self tableView:tableView index:kindex allowheadline:YES];
}

-(UITableViewCell *)tableView:(UITableView *)tableView index:(NSInteger)kindex allowheadline:(BOOL)kallow
{
    if(kindex==0&&kallow&&[self isHeadline])
    {
        //显示头条列表项
        static NSString *newsHeadlineCellIdentifier = @"NewsHeadlineCell";
        NewsHeadlineCell *cell = [tableView dequeueReusableCellWithIdentifier:newsHeadlineCellIdentifier];
        if(cell==nil)
        {
            NSArray *nib = [[NSBundle mainBundle]loadNibNamed:@"NewsHeadlineCell" owner:self options:nil];
            cell = [nib objectAtIndex:0];
        }
        [cell setData:self];
        return cell;
    }
    if ([StringUtil isEmpty:self.titlepic]&&[StringUtil isEmpty:self.titlepic_small]) {
        //显示无图列表项
        static NSString *newsNoPicCellIdentifier = @"NewsNoPicCell";
        NewsNoPicCell *cell = [tableView dequeueReusableCellWithIdentifier:newsNoPicCellIdentifier];
        if(cell==nil)
        {
            NSArray *nib = [[NSBundle mainBundle]loadNibNamed:@"NewsNoPicCell" owner:self options:nil];
            cell = [nib objectAtIndex:0];
        }
        [cell setData:self];
        return cell;
    }
    static NSString *newsNormCellIdentifier = @"NewsNormalCell";
    NewsNormalCell *cell = [tableView dequeueReusableCellWithIdentifier:newsNormCellIdentifier];
    if(cell==nil)
    {
        NSArray *nib = [[NSBundle mainBundle]loadNibNamed:@"NewsNormalCell" owner:self options:nil];
        cell = [nib objectAtIndex:0];
    }
    [cell setData:self];
    return cell;
}

-(NSString *)getCacheFileName
{
    NSString *ptime = [TimeUtil stringFromDateWithFormat:[TimeUtil dateFromString:self.publishtime] format:@"yyyyMMddHHmmss"];
    return [FileUtil createFilePathForLocation:FileDirectoryDocuments withFileName:[NSString stringWithFormat:@"nd%d_%@",self.Newsid,ptime] withExtension:@"cache"];
}

#pragma mark - BaseServiceDelegate
-(void)serviceResult:(ResponseVO *)result
{
    if(result.tag==CINT_TAG_GETNEWSDETAIL&&result.tag2==newsid)
    {
        if ([result isSucess]) {
            ResponseVO *rvo = [[ResponseVO alloc] init];
            NewsDetailVO *vo = [JsonParser parseJsonToEntity:result.msg respVO:&rvo ref:nil];
            if([rvo isSucess])
            {
                //缓存
                [result.msg writeToFile:[self getCacheFileName] atomically:YES encoding:NSUTF8StringEncoding error:nil];
                if(_comblock) _comblock(vo);
            }
            else
            {
                if(_errorblock) _errorblock(rvo.msg);
            }
        }
        else
        {
            if(_errorblock)
                _errorblock(result.msg);
        }
    }
}

@end

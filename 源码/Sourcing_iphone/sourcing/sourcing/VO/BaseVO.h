//
//  BaseVO.h
//  sourcing
//
//  Created by wuzhu on 12-12-25.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import <Foundation/Foundation.h>

@class BaseVO;

typedef void(^DetailInfoCompleteBlock)(BaseVO *kvo);
typedef void (^DetailInfoErrorBlock)(NSString *kmsg);

@interface BaseVO : NSObject
{
    @protected
    DetailInfoCompleteBlock _comblock;
    DetailInfoErrorBlock _errorblock;
}

@property NSInteger Id;

-(NSString *)getHtmlTitle;
-(NSString *)getHtmlSubTitle;
-(void)setHtmlToShow:(DetailInfoCompleteBlock)kcomblock failure:(DetailInfoErrorBlock)kerrorblock;
-(NSString *)generateShareText;
-(NSString *)getHtmlContext;
-(CGFloat)heightForCell:(NSInteger)kindex;
-(CGFloat)heightForCell:(NSInteger)kindex allowheadline:(BOOL)kallow;
-(UITableViewCell *)tableView:(UITableView *)tableView index:(NSInteger)kindex;
-(UITableViewCell *)tableView:(UITableView *)tableView index:(NSInteger)kindex allowheadline:(BOOL)kallow;

+(UITableViewCell *)tableViewWithEmptyData:(UITableView *)tableView title:(NSString *)ktitle;

@end



